package pl.mkwiecien.trimmit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["pl.mkwiecien.trimmit.config"])
class App

fun main(args: Array<String>) {
	runApplication<App>(*args)
}
