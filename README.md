# Coding Challenge

Command line application to help getting dressed and leaving the house

## Prerequisites
 * Scala
 * SBT
 * [Instructions for installing Scala and Sbt](https://www.scala-lang.org/download/)

## Getting Started

Clone the project

```
git clone https://github.com/dudelorf/codingchallenge
cd codingchallenge
```

## Running the application

### With Sbt

```
sbt "run command line, arguments,"
```

### From packaged application

Package the application
```
sbt package
```
Creates the following jar:
```
target\scala-2.12\codingchallenge_2.12-0.1.0-SNAPSHOT.jar
```
Run the application
```
scala path/to/jar command line arguments
```

## Tests

Run tests with SBT

```
sbt test
```