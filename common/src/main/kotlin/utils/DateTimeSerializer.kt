package utils

import kotlinx.serialization.KInput
import kotlinx.serialization.KOutput
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.SerialClassDescImpl

object DateTimeSerializer : KSerializer<DateTime> {

    override val serialClassDesc = SerialClassDescImpl("chat.DateTime")

    override fun save(output: KOutput, obj: DateTime) {
        output.writeStringValue(obj.toDateFormatString())
    }

    override fun load(input: KInput): DateTime = input.readStringValue().parseDate()
}
