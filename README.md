# Parser
Parses web server access log file, loads the log to MySQL and checks if a given IP makes more than a certain number of requests for the given duration.

Example command: 
    java -cp "parser.jar" com.ef.Parser --accesslog=/path/to/file --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100