import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import com.ericrkinzel.codingchallenge.commands.Command
import com.ericrkinzel.codingchallenge.processor.CommandProcessor
import com.ericrkinzel.codingchallenge.messages.messages.FAIL_MESSAGE
import com.ericrkinzel.codingchallenge.enums.Temp._

class CommandProcessorTests extends FunSuite with BeforeAndAfter {

  /**
   * Command processor to be tested
   */
  private var commandProcessor: CommandProcessor = _

  /**
   * Creates a command map for testing
   */
  private val commandMap = Map[String, Command](
    ("1", 
      new Command(
        "1",
        "test command 1",
        (t, c, cs, pcs) => {
          if(t == HOT)
            false
          else
            true
        },
        "command 1 hot msg",
        "command 1 cold msg",
      )
    ),
    ("2", 
      new Command(
        "2",
        "test command 2",
        (t, c, cs, pcs) => {
          if(pcs contains c)
            false
          else
            true
        },
        "command 2 hot msg",
        "command 2 cold msg",
      )
    )
  )

  /**
   * Message link for testing
   */
  private val msgLink = "-"

  before {
    // create a fresh command processor
    commandProcessor = new CommandProcessor(commandMap, msgLink)
  }

  test("input requires at least a temperature and a command") {
    assert(commandProcessor.execute(Array()) === FAIL_MESSAGE)
    assert(commandProcessor.execute(Array("COLD")) === FAIL_MESSAGE)
    assert(commandProcessor.execute(Array("COLD", "1")) !== FAIL_MESSAGE)
  }
  
  test("first input must be a temperature") {
    assert(commandProcessor.execute(Array("WRONG", "1", "2")) === FAIL_MESSAGE)
  }

  test("only valid commands are accepted") {
    assert(commandProcessor.execute(Array("COLD", "1", "2", "3")) === FAIL_MESSAGE)
  }

  test("will stop processing and produce fail message if command not valid") {
    val expectedMsg = Array(
      commandMap("2").getMsg(HOT),
      FAIL_MESSAGE
    ).mkString(msgLink)

    assert(commandProcessor.execute(Array("HOT", "2", "2")) === expectedMsg)
  }

  test("should produce full command message") {
    val expectedMsg = Array(
      commandMap("2").getMsg(COLD),
      commandMap("1").getMsg(COLD)
    ).mkString(msgLink)

    assert(commandProcessor.execute(Array("COLD", "2", "1")) === expectedMsg)
  }
}