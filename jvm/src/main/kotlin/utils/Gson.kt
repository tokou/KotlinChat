package utils

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import kotlin.reflect.KClass

val gson = GsonBuilder()
    .registerTypeAdapter(DateTime::class.java, DateTimeConverter())
    .serializeNulls()
    .create()!!

fun Any.toJson() = gson.toJson(this)!!

inline fun <reified T : Any> String.fromJson() = fromJson(T::class)

fun <T : Any> String.fromJson(clazz: KClass<T>) = try {
    gson.fromJson(this, clazz.java)
} catch (e: JsonSyntaxException) {
    println("Error in parsing \"$this\" to ${clazz.java}")
    null
}
