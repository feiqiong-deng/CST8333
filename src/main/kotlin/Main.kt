import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.IOException
import java.nio.file.Paths
import kotlin.io.path.bufferedReader

/**
 *
 */
fun main() {
    println("*************** Program by: Feiqiong DENG ***************")
    val list = ArrayList<PipelineRecord>()
    dataSetReader(list)
    printList(list)
}

/**
 *
 */
fun dataSetReader(list: ArrayList<PipelineRecord>) {
    try {
        val reader = Paths.get("src/main/resources/pipeline-incidents-comprehensive-data.csv").bufferedReader()
        val parser = CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())

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
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

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
