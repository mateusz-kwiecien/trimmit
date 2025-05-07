package pl.mkwiecien.trimmit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TrimmitApplication

fun main(args: Array<String>) {
	runApplication<TrimmitApplication>(*args)
}
