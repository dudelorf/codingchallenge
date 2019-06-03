import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import com.ericrkinzel.codingchallenge.processor.CommandProcessor
import com.ericrkinzel.codingchallenge.commands.commands._
import com.ericrkinzel.codingchallenge.messages.messages.FAIL_MESSAGE
import com.ericrkinzel.codingchallenge.enums.Temp._

/**
 * functional test for the whole program
 */
class CodingchallengeTest extends FunSuite with BeforeAndAfter {

  /**
   * Command processor to be tested
   */
  private var commandProcessor: CommandProcessor = _

  before {
    commandProcessor = new CommandProcessor()
  }

  test("example 1") {
    val expectedMsg = Array(
      commandMap(TAKE_OFF_PJS).getMsg(HOT),
      commandMap(PANTS).getMsg(HOT),
      commandMap(SHIRT).getMsg(HOT),
      commandMap(HEADWEAR).getMsg(HOT),
      commandMap(FOOTWEAR).getMsg(HOT),
      commandMap(LEAVE_HOUSE).getMsg(HOT)
    ).mkString(", ")

    val input = Array("HOT", "8,", "6,", "4,", "2,", "1,", "7,")

    assert(commandProcessor.execute(input) === expectedMsg)
  }

  test("example 2") {
    val expectedMsg = Array(
      commandMap(TAKE_OFF_PJS).getMsg(COLD),
      commandMap(PANTS).getMsg(COLD),
      commandMap(SOCKS).getMsg(COLD),
      commandMap(SHIRT).getMsg(COLD),
      commandMap(HEADWEAR).getMsg(COLD),
      commandMap(JACKET).getMsg(COLD),
      commandMap(FOOTWEAR).getMsg(COLD),
      commandMap(LEAVE_HOUSE).getMsg(COLD)
    ).mkString(", ")

    val input = Array("COLD", "8,", "6,", "3,", "4,", "2,", "5,", "1,", "7,")

    assert(commandProcessor.execute(input) === expectedMsg)
  }

  test("example 3") {
    val expectedMsg = Array(
      commandMap(TAKE_OFF_PJS).getMsg(HOT),
      commandMap(PANTS).getMsg(HOT),
      FAIL_MESSAGE
    ).mkString(", ")

    val input = Array("HOT", "8,", "6,", "6,")

    assert(commandProcessor.execute(input) === expectedMsg)
  }

  test("example 4") {
    val expectedMsg = Array(
      commandMap(TAKE_OFF_PJS).getMsg(HOT),
      commandMap(PANTS).getMsg(HOT),
      FAIL_MESSAGE
    ).mkString(", ")

    val input = Array("HOT", "8,", "6,", "3,")

    assert(commandProcessor.execute(input) === expectedMsg)
  }
  
  test("example 5") {
    val expectedMsg = Array(
      commandMap(TAKE_OFF_PJS).getMsg(COLD),
      commandMap(PANTS).getMsg(COLD),
      commandMap(SOCKS).getMsg(COLD),
      commandMap(SHIRT).getMsg(COLD),
      commandMap(HEADWEAR).getMsg(COLD),
      commandMap(JACKET).getMsg(COLD),
      FAIL_MESSAGE
    ).mkString(", ")

    val input = Array("COLD", "8,", "6,", "3,", "4,", "2,", "5,", "7,")

    assert(commandProcessor.execute(input) === expectedMsg)
  }
  
  test("example 6") {
    val expectedMsg = Array(
      FAIL_MESSAGE
    ).mkString(", ")

    val input = Array("COLD", "6,")

    assert(commandProcessor.execute(input) === expectedMsg)
  }
}