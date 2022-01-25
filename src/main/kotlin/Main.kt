import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.IOException
import java.nio.file.Paths
import kotlin.io.path.bufferedReader

/**
 * This is the entry point of the application.
 * @author Feiqiong DENG
 * In Kotlin language, the main method is not a method inside the class.
 * In this main method, an empty arraylist of PipelineRecord is declared.
 * Then the methods of reading the csv file and print records are called.
 */
fun main() {
    println("*************** Program by: Feiqiong DENG ***************")
    val list = ArrayList<PipelineRecord>()
    dataSetReader(list)
    printList(list)
    println("*************** Total records printed is: ${list.size}. @author: Feiqiong DENG ***************")
}

/**
 * This is a function to read the dataset file.
 * @author Feiqiong DENG
 * @param list The ArrayList input for the function and will be populated by the records of the dataset file.
 * This function is firstly to read the csv file in the resource folder by using bufferedReader.
 * CSVParser is from the Apache Commons library and can handle reading and writing csv file in Kotlin.
 * Getting the values of specific columns and stored in PipelineRecord object.
 * Each PipelineRecord object is added to the list.
 */
fun dataSetReader(list: ArrayList<PipelineRecord>) {
    /**
     * Locate the csv file and use bufferedReader to read the file.
     * Initialize the CSVParser instance and set to ignore the header row, case-insensitive and trim the records.
     */
    try {
        val reader = Paths.get("src/main/resources/pipeline-incidents-comprehensive-data.csv").bufferedReader()
        val parser = CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())

        /**
         * Iterate over the parser instance and extract records specific columns value from it.
         * Column records values are transferred and stored in a PipelineRecord object.
         * Each PipelineRecord object is added to the arraylist.
         */
        try {
            for (record in parser) {
                val number = record.get(0)
                val type = record.get(1)
                val date = record.get(2)
                val centre = record.get(3)
                val province = record.get(4)
                val company = record.get(5)
                val substance = record.get(10)
                val significant = record.get(12)
                val category = record.get(17)

                list.add(
                    PipelineRecord(
                        number,
                        type,
                        date,
                        centre,
                        province,
                        company,
                        substance,
                        significant,
                        category
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        reader.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

/**
 * This a function to print the results.
 * @author Feiqiong DENG
 * @param list The ArrayList input for the function and elements in the list will be printed.
 * Format the PipelineRecord object in the list and print the records.
 */
fun printList(list: ArrayList<PipelineRecord>) {
    list.forEach {
        println(
            "%s, %s, %s, %s, %s, %s, %s, %s, %s".format
                (
                it.number,
                it.type,
                it.date,
                it.centre,
                it.province,
                it.company,
                it.substance,
                it.significant,
                it.category
            )
        )
    }
}
