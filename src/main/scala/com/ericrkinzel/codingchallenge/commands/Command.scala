package com.ericrkinzel.codingchallenge.commands

import com.ericrkinzel.codingchallenge.enums.Temp._

/**
 * A command with associated messages and validation
 *
 * @Constructor creates a new command
 * @param key unique identifer for command
 * @param description description of command
 * @param validator validation function to determine if command can be used
 *  takes temperature, key, remaining commands and and previously processed commands
 * @param hotMsg message to be used when it is hot
 * @param coldMsg message to be used when it is cold
 */
class Command(
  val key: String,
  val description: String,
  private val validator: ((Temp, String, Array[String], Array[String]) => Boolean),
  private val hotMsg: String,
  private val coldMsg: String) {

    /**
     * Checks if command can be used given previously processed commands
     *
     * @param t temperature context of command
     * @param cs unprocessed commands
     * @param pcs previously processed commands
     * @return if command can be used
     */
    def validate(t: Temp, cs: Array[String], pcs: Array[String]): Boolean = {
      validator(t, key, cs, pcs)
    }

    /**
     * Gets temperature appropriate message
     *
     * default to an empty string if invalid value supplied
     *
     * @param t temperature context
     */
    def getMsg(t: Temp): String = t match {
        case HOT => hotMsg
        case COLD => coldMsg
        case _ => ""
    }
}