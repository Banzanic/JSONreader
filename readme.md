##How to run
To run the application you need to run class AppRunner which contains the main method. It takes the input from "/src/main/resources/JSONExample.json" and runs function on it. There is already an example of JSON in it, but feel free to change it and provide your own input.
##Method veryfying the input JSON data
The whole logic of a program is located in JSONReader class.
##Unit tests
In class JSONReaderTest are located all unit tests. I covered happy path and edge cases such as: empty file, empty JSON, field resource has empty value, field resource has more than one asterisk in the value, field resource has null value, field resource has zero asterisks in the value, no field resource in JSON, JSON has wrong format, file provided via file path was not found, null was given as argument to method "isSingleAsterisk"
