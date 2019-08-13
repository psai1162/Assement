package com.search.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static com.search.util.ConstantUtils.INDEX_CREATED_PATH;
import static com.search.util.ConstantUtils.INPUT_DIRECTORY_PATH;

public class CreateIndexFromInputFiles {

    public static void createIndexFile(){

        //Input Path Variable
        final Path inputDocDirectory = Paths.get(INPUT_DIRECTORY_PATH);

        try
        {
            //org.apache.lucene.store.Directory instance
            Directory dir = FSDirectory.open( Paths.get(INDEX_CREATED_PATH) );

            //analyzer with the default stop words
            Analyzer analyzer = new StandardAnalyzer();

            //IndexWriter Configuration
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            //IndexWriter writes new index files to the directory
            IndexWriter writer = new IndexWriter(dir, iwc);

            //Its recursive method to iterate all files and directories
            indexDocs(writer, inputDocDirectory);

            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    static void indexDocs(final IndexWriter writer, Path inputDirectoryPath) throws IOException
    {
        //Directory?
        if (Files.isDirectory(inputDirectoryPath))
        {
            //Iterate directory
            Files.walkFileTree(inputDirectoryPath, new SimpleFileVisitor<Path>()
            {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
                {
                    try
                    {
                        //Index this file
                        indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
                    }
                    catch (IOException ioe)
                    {
                        ioe.printStackTrace();
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        else
        {
            //Index this file
            indexDoc(writer, inputDirectoryPath, Files.getLastModifiedTime(inputDirectoryPath).toMillis());
        }
    }

    static void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException
    {
        try (InputStream stream = Files.newInputStream(file))
        {
            //Create lucene Document
            Document doc = new Document();

            doc.add(new StringField("path", file.toString(), Field.Store.YES));
            doc.add(new LongPoint("modified", lastModified));
            doc.add(new TextField("contents", new String(Files.readAllBytes(file)), Field.Store.YES));

            //Updates a document by first deleting the document(s)
            //containing <code>term</code> and then adding the new
            //document.  The delete and then add are atomic as seen
            //by a reader on the same index
            writer.updateDocument(new Term("path", file.toString()), doc);
        }
    }
}
