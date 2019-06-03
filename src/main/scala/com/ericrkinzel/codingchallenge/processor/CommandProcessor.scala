package com.ericrkinzel.codingchallenge.processor

import com.ericrkinzel.codingchallenge.messages.messages.FAIL_MESSAGE
import com.ericrkinzel.codingchallenge.enums.Temp
import com.ericrkinzel.codingchallenge.enums.Temp._
import com.ericrkinzel.codingchallenge.commands.Command
import com.ericrkinzel.codingchallenge.commands.commands.{commandMap => realCommandMap}

/**
 * Processes a list of commands
 *
 * @constructor creates a new command processor
 * @param commandMap optional map of accepted commands, defaults to predefined list
 * @param msgLink optional link between message in output
 */
class CommandProcessor(
  val commandMap: Map[String, Command] = realCommandMap,
  val msgLink: String = ", "
  ) {

  /**
   * Processes list of commands
   *
   * @param commands list of comands to process
   * @return output with all command messages
   */
  def execute(commands: Array[String]): String = {
    // Initial sanity check
    if(!inputValid(commands))
      FAIL_MESSAGE

    else
      getCommandMessages(
        Temp.withName(commands.head),
        commands.tail.map(cleanInput)
      ).mkString(msgLink)
  }

  /**
   * Conducts precondition check of commands
   *
   * Basic sanity check to make sure input is valid
   *
   * @param commands all input commands
   * @return if precondition check passes
   */
  private def inputValid(commands: Array[String]): Boolean = {

    // need to have at least 2 commands: temp and command
    if(commands.length == 0 || commands.length == 1)
      false

    // need a valid temperature
    else if(!Temp.values.map(_.toString).contains(commands.head))
      false

    // make sure all commands are valid
    else if(!Set(commands.tail.map(cleanInput): _*).subsetOf(commandMap.keySet))
      false

    // good to go
    else
      true
  }

  /**
   * Removes extra characters from input commands
   *
   * @param command command to clean
   */
  private def cleanInput(command: String): String = {
    return command.replace(",", "")
  }

  /**
   * Gets messages for all commands
   *
   * Invalid commands result in a failure message and termination of command
   * processing
   *
   * @param t temperature to be used
   * @param cs commands to be processed
   * @return list of messages for commands
   */
  private def getCommandMessages(t: Temp, cs: Array[String]): Array[String] = {

    /**
     * Master command processing function
     *
     * @param t temperature context of commands
     * @param cs list of commands to process
     * @param pcs previously processed commands
     * @param msgs messages from previously processed command messages
     * @return list of messages for all unprocessed commands
     */
    def go(t: Temp, cs: Array[String], pcs: Array[String], msgs: Array[String]): Array[String] = {
      // finished processing
      if(cs.length == 0)
        msgs

      // invalid command
      else if (!commandMap(cs.head).validate(t, cs, pcs))
        msgs :+ FAIL_MESSAGE

      // continue processing
      else
        go(t, cs.tail, pcs :+ cs.head, msgs :+ commandMap(cs.head).getMsg(t))
    }

    go(t, cs, Array(), Array())
  }

}
