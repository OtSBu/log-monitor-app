# log-monitor-app
Application for monitoring and analyzing logs.

Simple application that analyzes log files and tracks job processing times, generating warnings and errors if jobs exceed predefined duration thresholds.

Features
Parse log files (CSV format).
Track job start and end times based on process PIDs.
Calculate job durations to identify slow processes.
Generate warnings for jobs exceeding 5 minutes.
Log errors for jobs exceeding 10 minutes.
How to run it
Place your log file (logs.log) in the project directory.
Run the application: **java -jar LogMonitor.jar
