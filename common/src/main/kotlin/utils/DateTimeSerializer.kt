package utils

import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl

object DateTimeSerializer : KSerializer<DateTime> {

    override val serialClassDesc = SerialClassDescImpl("utils.DateTime")

    override fun save(output: KOutput, obj: DateTime) {
        output.writeStringValue(obj.toDateFormatString())
    }

    override fun load(input: KInput): DateTime = input.readStringValue().parseDate()
}
