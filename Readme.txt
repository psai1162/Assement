Overview
This service handles searching a term from set of documents.
This service provides three different approaches to achieve this.
1.Simple string matching
2.Text search using regular expressions
3.Preprocess the content and then search the index

Developed a spring boot application with three API with same signature to perform this task using these approaches.

Operations

1. String Match Search
Basic Overview: Searches the given string in set of documents using simple equals method.
Request Method: PUT
EndPoint: /stringMatchSearch
Request Body:
 searchString: used to perform search against the file and returns the count of number of occurrence of of string in documents
Sample Request:
{
"searchString":"string"
}
Sample Response:
{
    "searchResult": [
        "file1.txt - X matches",
        "file2.txt - X matches",
        "file3.txt - X matches",
    ]
}

2. Regular Expression Search
Basic Overview: Searches the given string in set of documents using Regular Expression pattern Match.
Request Method: PUT
EndPoint: /regularExpressionSearch
Request Body:
 searchString: used to perform search against the file and returns the count of number of occurrence of of string in documents
Sample Request:
{
"searchString":"string"
}
Sample Response:
{
    "searchResult": [
        "file1.txt - X matches",
        "file2.txt - X matches",
        "file3.txt - X matches",
    ]
}

3. Regular Expression Search
Basic Overview: Searches the given string in set of documents using Inverted Index Algorithm.
Request Method: PUT
EndPoint: /indexedSearch
Request Body:
 searchString: used to perform search against the file and returns the count of number of occurrence of of string in documents
Sample Request:
{
"searchString":"string"
}
Sample Response:
{
    "searchResult": [
        "file1.txt - X matches",
        "file2.txt - X matches",
        "file3.txt - X matches",
    ]
}