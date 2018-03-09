package utils

import com.google.gson.*
import java.lang.reflect.Type

abstract class StringJsonConverter<T> : JsonSerializer<T>, JsonDeserializer<T> {

    abstract fun toString(o: T): String?

    abstract fun fromString(o: String): T?

    override fun serialize(o: T, typeOfSrc: Type, jsonContext: JsonSerializationContext): JsonElement =
            JsonPrimitive(toString(o))

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, jsonContext: JsonDeserializationContext): T? =
            if (json.isJsonNull || json.asString.isEmpty()) null else fromString(json.asString)
}

class DateTimeConverter : StringJsonConverter<DateTime>() {

    override fun toString(o: DateTime): String? = o.toDateFormatString()

    override fun fromString(o: String): DateTime? = o.parseDate()
}
