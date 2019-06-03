package com.ericrkinzel.codingchallenge.commands

import com.ericrkinzel.codingchallenge.messages.messages.FAIL_MESSAGE
import com.ericrkinzel.codingchallenge.enums.Temp._

/**
 * Holds master collection of valid commands
 */
package object commands {

  /* Nice names for command values */
  val FOOTWEAR = "1"
  val HEADWEAR = "2"
  val SOCKS = "3"
  val SHIRT = "4"
  val JACKET = "5"
  val PANTS = "6"
  val LEAVE_HOUSE = "7"
  val TAKE_OFF_PJS = "8"

  /**
   * Master collection of commands
   *
   * Commands are mapped to command key. Provides full command definitions
   * including validation functions.
   */
  val commandMap = Map[String, Command](
    (FOOTWEAR,
      new Command(
        FOOTWEAR,
        "Put on footwear",
        (t, c, cs, pcs) => {
          // can't put on footwear twice
          if(pcs contains c)
            false
          // need to put on pants before footwear
          else if(!(pcs contains PANTS))
            false
          // need to have put on socks when cold
          else if(t == COLD && !(pcs contains SOCKS))
            false
          // can't be last command
          else if(cs.length == 1)
            false
          else
            true
        },
        "sandals",
        "boots"
      )
    ),

    (HEADWEAR,
      new Command(
        HEADWEAR,
        "Put on headwear",
        (t, c, cs, pcs) => {
          // can't put on headwear twice
          if(pcs contains c)
            false
          // need a shirt before putting on headwear
          else if(!(pcs contains SHIRT))
            false
          // can't be last command
          else if(cs.length == 1)
            false
          else
            true
        },
        "sunglasses",
        "hat"
      )
    ),

    (SOCKS,
      new Command(
        SOCKS,
        "Put on socks",
        (t, c, cs, pcs) => {
          // can't put on socks twice
          if(pcs contains c)
            false
          // need to have taken off pjs
          else if(!(pcs contains TAKE_OFF_PJS))
            false
          // can't put on socks when hot
          else if(t == HOT)
            false
          // can't be last command
          else if(cs.length == 1)
            false
          else
            true
        },
        FAIL_MESSAGE,
        "socks"
      )
    ),

    (SHIRT,
      new Command(
        SHIRT,
        "Put on shirt",
        (t, c, cs, pcs) => {
          // can't put on shirt twice
          if(pcs contains c)
            false
          // need to have taken off pjs
          else if(!(pcs contains TAKE_OFF_PJS))
            false
          // can't be last command
          else if(cs.length == 1)
            false
          else
            true
        },
        "shirt",
        "shirt"
      )
    ),

    (JACKET,
      new Command(
        JACKET,
        "Put on jacket",
        (t, c, cs, pcs) => {
          // can't put on jacket twice
          if(pcs contains c)
            false
          // need a shirt before a jacket
          else if(!(pcs contains SHIRT))
            false
          // can't put on jacket when hot
          else if(t == HOT)
            false
          // can't be last command
          else if(cs.length == 1)
            false
          else
            true
        },
        FAIL_MESSAGE,
        "jacket"
      )
    ),

    (PANTS,
      new Command(
        PANTS,
        "Put on pants",
        (t, c, cs, pcs) => {
          // can't put on pants twice
          if(pcs contains c)
            false
          // need to have taken off pjs
          else if(!(pcs contains TAKE_OFF_PJS))
            false
          // can't be last command
          else if(cs.length == 1)
            false
          else
            true
        },
        "shorts",
        "pants"
      )
    ),

    (LEAVE_HOUSE,
      new Command(
        LEAVE_HOUSE,
        "Leave house",
        (t, c, cs, pcs) => {
          // must be last command
          if(cs.length != 1)
            false
          // need to have on all items of clothing for temperature before leaving
          else if(t == HOT && Set(FOOTWEAR, HEADWEAR, SHIRT, PANTS).subsetOf(Set(pcs: _*)))
            true
          else if(t == COLD && Set(SOCKS, FOOTWEAR, HEADWEAR, SHIRT, JACKET, PANTS).subsetOf(Set(pcs: _*)))
            true
          else
            false
        },
        "leaving house",
        "leaving house"
      )
    ),

    (TAKE_OFF_PJS,
      new Command(
        TAKE_OFF_PJS,
        "Take off pajamas",
        (t, c, cs, pcs) => {
          // can't take of pjs twice
          if(pcs contains c)
            false
          // can't be last command
          else if(cs.length == 1)
            false
          else
            true
        },
        "Removing PJs",
        "Removing PJs"
      )
    )
  )
}