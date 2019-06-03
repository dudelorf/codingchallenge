package com.ericrkinzel.codingchallenge

import com.ericrkinzel.codingchallenge.processor.CommandProcessor
import com.ericrkinzel.codingchallenge.commands.commands.commandMap

/**
 * Executes command processing program
 *
 * Takes a list of commands starting with a temperature followed by any number
 * of command values.
 * Ex.
 *  HOT 8, 6, 4, 2, 1, 7
 * Outputs message from processing all supplied commands
 */
object Main extends App {
  println(new CommandProcessor().execute(args))
}
