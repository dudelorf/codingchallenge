import org.scalatest.FunSpec
import com.ericrkinzel.codingchallenge.commands.Command
import com.ericrkinzel.codingchallenge.commands.commands._
import com.ericrkinzel.codingchallenge.enums.Temp._
import com.ericrkinzel.codingchallenge.messages.messages.FAIL_MESSAGE

/**
 * Tests for the command map
 */
class CommandPackageTests extends FunSpec {

  describe("footwear command") {
    it("should have the expected key") {
      assert(FOOTWEAR === "1")
    }
    it("should require socks if cold") {
      assert(commandMap(FOOTWEAR).validate(HOT, Array(FOOTWEAR, LEAVE_HOUSE), Array(PANTS)) === true)
      assert(commandMap(FOOTWEAR).validate(COLD, Array(FOOTWEAR, LEAVE_HOUSE), Array(PANTS)) === false)
    }
    it("should require pants first") {
      assert(commandMap(FOOTWEAR).validate(HOT, Array(FOOTWEAR, LEAVE_HOUSE), Array()) === false)
      assert(commandMap(FOOTWEAR).validate(COLD, Array(FOOTWEAR, LEAVE_HOUSE), Array(SOCKS)) === false)
    }
    it("should allow if pants and socks put on first when cold") {
      assert(commandMap(FOOTWEAR).validate(COLD, Array(FOOTWEAR, LEAVE_HOUSE), Array(PANTS, SOCKS)) === true)
    }
    it("should allow if pants put on first when hot") {
      assert(commandMap(FOOTWEAR).validate(HOT, Array(FOOTWEAR, LEAVE_HOUSE), Array(PANTS)) === true)
    }
    it("can't be the last command") {
      assert(commandMap(FOOTWEAR).validate(COLD, Array(FOOTWEAR), Array(PANTS, SOCKS)) === false)
      assert(commandMap(FOOTWEAR).validate(HOT, Array(FOOTWEAR), Array(PANTS)) === false)
    }
    it("should not allow putting on twice") {
      assert(commandMap(FOOTWEAR).validate(HOT, Array(FOOTWEAR, LEAVE_HOUSE), Array(PANTS, FOOTWEAR)) === false)
      assert(commandMap(FOOTWEAR).validate(COLD, Array(FOOTWEAR, LEAVE_HOUSE), Array(PANTS, SOCKS, FOOTWEAR)) === false)
    }
    it("should have the expected messages") {
      assert(commandMap(FOOTWEAR).getMsg(HOT) === "sandals")
      assert(commandMap(FOOTWEAR).getMsg(COLD) === "boots")
    }
  }

  describe("headwear command") {
    it("should have the expected key") {
      assert(HEADWEAR === "2")
    }
    it("should require a shirt") {
      assert(commandMap(HEADWEAR).validate(HOT, Array(HEADWEAR, LEAVE_HOUSE), Array()) === false)
      assert(commandMap(HEADWEAR).validate(COLD, Array(HEADWEAR, LEAVE_HOUSE), Array()) === false)
    }
    it("should not allow putting on twice") {
      assert(commandMap(HEADWEAR).validate(HOT, Array(HEADWEAR, LEAVE_HOUSE), Array(SHIRT, HEADWEAR)) === false)
      assert(commandMap(HEADWEAR).validate(COLD, Array(HEADWEAR, LEAVE_HOUSE), Array(SHIRT, HEADWEAR)) === false)
    }
    it("should allow if shirt put on") {
      assert(commandMap(HEADWEAR).validate(HOT, Array(HEADWEAR, LEAVE_HOUSE), Array(SHIRT)) === true)
      assert(commandMap(HEADWEAR).validate(COLD, Array(HEADWEAR, LEAVE_HOUSE), Array(SHIRT)) === true)
    }
    it("can't be the last command") {
      assert(commandMap(HEADWEAR).validate(HOT, Array(HEADWEAR), Array(SHIRT)) === false)
      assert(commandMap(HEADWEAR).validate(COLD, Array(HEADWEAR), Array(SHIRT)) === false)
    }
    it("should have the expected messages") {
      assert(commandMap(HEADWEAR).getMsg(HOT) === "sunglasses")
      assert(commandMap(HEADWEAR).getMsg(COLD) === "hat")
    }
  }

  describe("socks command") {
    it("should have the expected key") {
      assert(SOCKS === "3")
    }
    it("should allow if having taken off pjs and not hot") {
      assert(commandMap(SOCKS).validate(HOT, Array(SOCKS, LEAVE_HOUSE), Array(TAKE_OFF_PJS)) === false)
      assert(commandMap(SOCKS).validate(COLD, Array(SOCKS, LEAVE_HOUSE), Array(TAKE_OFF_PJS)) === true)
    }
    it("should not allow if not having taken off pjs") {
      assert(commandMap(SOCKS).validate(HOT, Array(SOCKS, LEAVE_HOUSE), Array()) === false)
      assert(commandMap(SOCKS).validate(COLD, Array(SOCKS, LEAVE_HOUSE), Array()) === false)
    }
    it("should not allow putting on twice") {
      assert(commandMap(SOCKS).validate(HOT, Array(SOCKS, LEAVE_HOUSE), Array(TAKE_OFF_PJS, SOCKS)) === false)
      assert(commandMap(SOCKS).validate(COLD, Array(SOCKS, LEAVE_HOUSE), Array(TAKE_OFF_PJS, SOCKS)) === false)
    }
    it("can't be the last command") {
      assert(commandMap(SOCKS).validate(COLD, Array(SOCKS), Array(TAKE_OFF_PJS)) === false)
    }
    it("should have the expected messages") {
      assert(commandMap(SOCKS).getMsg(HOT) === FAIL_MESSAGE)
      assert(commandMap(SOCKS).getMsg(COLD) === "socks")
    }
  }

  describe("shirt command") {
    it("should have the expected key") {
      assert(SHIRT === "4")
    }
    it("should require having taken off pjs") {
      assert(commandMap(SHIRT).validate(HOT, Array(SHIRT, LEAVE_HOUSE), Array()) === false)
      assert(commandMap(SHIRT).validate(COLD, Array(SHIRT, LEAVE_HOUSE), Array()) === false)
    }
    it("should not allow putting on twice") {
      assert(commandMap(SHIRT).validate(HOT, Array(SHIRT, LEAVE_HOUSE), Array(TAKE_OFF_PJS, SHIRT)) === false)
      assert(commandMap(SHIRT).validate(COLD, Array(SHIRT, LEAVE_HOUSE), Array(TAKE_OFF_PJS, SHIRT)) === false)
    }
    it("should allow if having taken off pjs") {
      assert(commandMap(SHIRT).validate(HOT, Array(SHIRT, LEAVE_HOUSE), Array(TAKE_OFF_PJS)) === true)
      assert(commandMap(SHIRT).validate(COLD, Array(SHIRT, LEAVE_HOUSE), Array(TAKE_OFF_PJS)) === true)
    }
    it("can't be the last command") {
      assert(commandMap(SHIRT).validate(HOT, Array(SHIRT), Array(TAKE_OFF_PJS)) === false)
      assert(commandMap(SHIRT).validate(COLD, Array(SHIRT), Array(TAKE_OFF_PJS)) === false)
    }
    it("should have the expected messages") {
      assert(commandMap(SHIRT).getMsg(HOT) === "shirt")
      assert(commandMap(SHIRT).getMsg(COLD) === "shirt")
    }
  }

  describe("jacket command") {
    it("should have the expected key") {
      assert(JACKET === "5")
    }
    it("should require a shirt") {
      assert(commandMap(JACKET).validate(HOT, Array(JACKET, LEAVE_HOUSE), Array()) === false)
      assert(commandMap(JACKET).validate(COLD, Array(JACKET, LEAVE_HOUSE), Array()) === false)
    }
    it("should not allow if hot") {
      assert(commandMap(JACKET).validate(HOT, Array(JACKET, LEAVE_HOUSE), Array(SHIRT)) === false)
    }
    it("should allow if cold and a shirt put on") {
      assert(commandMap(JACKET).validate(COLD, Array(JACKET, LEAVE_HOUSE), Array(SHIRT)) === true)
    }
    it("can't be the last command") {
      assert(commandMap(JACKET).validate(COLD, Array(JACKET), Array(SHIRT)) === false)
    }
    it("should not allow if already put on") {
      assert(commandMap(JACKET).validate(COLD, Array(JACKET, LEAVE_HOUSE), Array(SHIRT, JACKET)) === false)
    }
    it("should have the expected messages") {
      assert(commandMap(JACKET).getMsg(HOT) === FAIL_MESSAGE)
      assert(commandMap(JACKET).getMsg(COLD) === "jacket")
    }
  }

  describe("pants command") {
    it("should have the expected key") {
      assert(PANTS === "6")
    }
    it("should require having taken off pjs") {
      assert(commandMap(PANTS).validate(HOT, Array(PANTS, LEAVE_HOUSE), Array()) === false)
      assert(commandMap(PANTS).validate(COLD, Array(PANTS, LEAVE_HOUSE), Array()) === false)
    }
    it("should allow if having taken off pjs") {
      assert(commandMap(PANTS).validate(HOT, Array(PANTS, LEAVE_HOUSE), Array(TAKE_OFF_PJS)) === true)
      assert(commandMap(PANTS).validate(COLD, Array(PANTS, LEAVE_HOUSE), Array(TAKE_OFF_PJS)) === true)
    }
    it("can't be the last command"){
      assert(commandMap(PANTS).validate(HOT, Array(PANTS), Array(TAKE_OFF_PJS)) === false)
      assert(commandMap(PANTS).validate(COLD, Array(PANTS), Array(TAKE_OFF_PJS)) === false)
    }
    it("should not allow putting on twice") {
      assert(commandMap(PANTS).validate(HOT, Array(PANTS, LEAVE_HOUSE), Array(TAKE_OFF_PJS, PANTS)) === false)
      assert(commandMap(PANTS).validate(COLD, Array(PANTS, LEAVE_HOUSE), Array(TAKE_OFF_PJS, PANTS)) === false)
    }
    it("should have the expected messages") {
      assert(commandMap(PANTS).getMsg(HOT) === "shorts")
      assert(commandMap(PANTS).getMsg(COLD) === "pants")
    }
  }

  describe("leave house command") {
    it("should have the expected key") {
      assert(LEAVE_HOUSE === "7")
    }
    it("should require all appropriate clothing") {
      assert(commandMap(LEAVE_HOUSE).validate(HOT, Array(LEAVE_HOUSE), Array(FOOTWEAR, HEADWEAR, SHIRT, PANTS)) === true)
      assert(commandMap(LEAVE_HOUSE).validate(COLD, Array(LEAVE_HOUSE), Array(SOCKS, FOOTWEAR, HEADWEAR, SHIRT, JACKET, PANTS)) === true)

      assert(commandMap(LEAVE_HOUSE).validate(HOT, Array(LEAVE_HOUSE), Array(FOOTWEAR, SHIRT, PANTS)) === false)
      assert(commandMap(LEAVE_HOUSE).validate(COLD, Array(LEAVE_HOUSE), Array(SOCKS, FOOTWEAR, SHIRT, JACKET, PANTS)) === false)
    }
    it("must be the last command") {
      assert(commandMap(LEAVE_HOUSE).validate(HOT, Array(LEAVE_HOUSE, PANTS), Array(FOOTWEAR, HEADWEAR, SHIRT, PANTS)) === false)
      assert(commandMap(LEAVE_HOUSE).validate(COLD, Array(LEAVE_HOUSE, PANTS), Array(SOCKS, FOOTWEAR, HEADWEAR, SHIRT, JACKET, PANTS)) === false)
    }
    it("should have the expected messages") {
      assert(commandMap(LEAVE_HOUSE).getMsg(HOT) === "leaving house")
      assert(commandMap(LEAVE_HOUSE).getMsg(COLD) === "leaving house")
    }
  }

  describe("take off pjs command") {
    it("should have the expected key") {
      assert(TAKE_OFF_PJS === "8")
    }
    it("should not allow taking off pjs twice") {
      assert(commandMap(TAKE_OFF_PJS).validate(HOT, Array(TAKE_OFF_PJS, LEAVE_HOUSE), Array(TAKE_OFF_PJS)) === false)
      assert(commandMap(TAKE_OFF_PJS).validate(COLD, Array(TAKE_OFF_PJS, LEAVE_HOUSE), Array(TAKE_OFF_PJS)) === false)
    }
    it("should allow taking off pjs before anything else") {
      assert(commandMap(TAKE_OFF_PJS).validate(HOT, Array(TAKE_OFF_PJS, LEAVE_HOUSE), Array()) === true)
      assert(commandMap(TAKE_OFF_PJS).validate(COLD, Array(TAKE_OFF_PJS, LEAVE_HOUSE), Array()) === true)
    }
    it("can't be the last command") {
      assert(commandMap(TAKE_OFF_PJS).validate(HOT, Array(TAKE_OFF_PJS), Array()) === false)
      assert(commandMap(TAKE_OFF_PJS).validate(COLD, Array(TAKE_OFF_PJS), Array()) === false)
    }
    it("should have the expected messages") {
      assert(commandMap(TAKE_OFF_PJS).getMsg(HOT) === "Removing PJs")
      assert(commandMap(TAKE_OFF_PJS).getMsg(COLD) === "Removing PJs")
    }
  }
}