package utils

import org.koin.log.Logger

class KtorKoinLogger(
    private val logger: org.slf4j.Logger
) : Logger {
    override fun debug(msg: String) {
        logger.debug(msg)
    }

    override fun err(msg: String) {
        logger.error(msg)
    }

    override fun log(msg: String) {
        logger.info(msg)
    }
}
