# Call Center project

The project is a call center architecture design and coding exercise

[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity)
[![made-with-java 14](https://img.shields.io/badge/Made%20with-Java-1f425f.svg)](https://www.java.com/en/)

# Overview

A call center has three employees. The calls need to handle in hierachial fashion and subordinates need to be able to pass calls to their supervising manager.

The present project using Concurrency to handle the work of taking calls for the call center. The calls are handled by employees who are themselves run on threads. The implementation takes the ExecutorService and creates threads that can then be stopped when desired. This also 
allows the use of Futures when desiring to know when a particular job is completed.


## Class Diagram

 ![alt text](https://github.com/Flea00012/call-center/blob/master/Diagrams/Package%20callcenter.svg)

