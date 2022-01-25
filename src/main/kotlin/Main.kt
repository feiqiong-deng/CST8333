import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.nio.file.Paths
import kotlin.io.path.bufferedReader

fun main() {
//    println("Hello World!")
    dataSetReader()
}

fun dataSetReader() {
    val reader = Paths.get("src/main/resources/pipeline-incidents-comprehensive-data.csv").bufferedReader()

    val parser = CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())

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
        println(PipelineRecord(number, type, date, centre, province, company, substance, significant, category).toString())
    }
}
