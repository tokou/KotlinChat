package utils

import external.enzyme.Adapter
import external.enzyme.configure
import kotlin.test.BeforeTest

interface ReactTest {

    @BeforeTest
    fun configureEnzyme() {
        configure(object {
            val adapter = Adapter()
        })
    }
}
