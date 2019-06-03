import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import com.ericrkinzel.codingchallenge.commands.Command
import com.ericrkinzel.codingchallenge.enums.Temp._

class CommandTests extends FunSuite with BeforeAndAfter {

  private var command: Command = _

  before {
    command = new Command(
      "1",
      "command description",
      (t, c, cs, pcs) => {
        if(t == HOT)
          false
        else if(pcs contains c)
          false
        else
          true
      },
      "hot msg",
      "cold msg"
    )
  }

  test("validation function should determine if command can be used") {
    assert(command.validate(HOT, Array(), Array()) === false)
    assert(command.validate(COLD, Array(), Array("1", "2")) === false)
    assert(command.validate(COLD, Array(), Array("2", "3")) === true)
  }

  test("getting the appropriate command message") {
    assert(command.getMsg(HOT) === "hot msg")
    assert(command.getMsg(COLD) === "cold msg")
    assert(command.getMsg(null) === "")
  }
}
