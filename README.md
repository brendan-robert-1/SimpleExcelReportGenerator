# Simple Excel Report Generator

This project is a thin wrapper on Apache POI, a library designed for creating excel files in Java. This is designed to streamline the most common use case for POI which is to take a Collection of record or struct type objects and convert them into a table with headers. To demonstrate how simple this is lets look at a bare bones example:
```java
List<DemoRecord> records = getData();  //where getData() is some method that gets whatever data we want
Report<DemoRecord> report = new Report<>(records, "Testing Record");
report.writeReportToFile(new File("workbook.xlsx"));
```

Here we have some collection of classes that represent our data structure, we create a ```Report<DemoRecord>``` and we pass in the records and a name for our sheet to the constructor. We can then write our report to a file. This will be formatted with the default formatter and produce a sheet like so:

![alt text](https://i.gyazo.com/39b6e245b01e7d53a526f59ac281f8be.png)

The ```DemoRecord``` is our record type that has fields that like:

```java
public class DemoRecord {

    @RecordElement(name = "User Name")
    private String name;

    private int userAge;

    @RecordElement(include = false)
    private int social;

    private String longFieldNameCamel;
```

No annotations are required to create a `Report<DemoRecord>`. The default behavior is to use the field names as headers. The fields are split by camel case convention and uppercased. longFieldNameCamel -> Long Field Name Camel. Annotations can be used to override this behavior and supply a different header name. Additionally the `include = false` annotation directive can be applied to omit a field from the final report.

The style as seen above is default if none are provided. If instead you wish to define your own style you may create a custom `ReportStyleFunction` and pass it into
```java 
report.getXSSFReport(ReportStyleFunction fx)
``` 


