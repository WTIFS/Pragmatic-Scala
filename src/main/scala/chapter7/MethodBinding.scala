/*
 * Copyright 2018 pragmatic-scala.reactiveplatform.xyz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package chapter7

object MethodBinding extends App {
  // #snip_1
  abstract class Writer {
    // 这个例子和上章的区别是，上章的 check 在抽象类里有实现，这里的 writeMessage 没有实现
    def writeMessage(message: String): Unit
  }
  // #snip_1

  // #snip_2
  trait UpperCaseWriter extends Writer {
    // 因此 trait 里实现该方法时必须加上 abstract override
    abstract override def writeMessage(message: String): Unit =
      super.writeMessage(message.toUpperCase)
  }

  trait ProfanityFilteredWriter extends Writer {
    abstract override def writeMessage(message: String): Unit =
      super.writeMessage(message.replace("stupid", "s-----"))
  }
  // #snip_2

  // #snip_3
  class StringWriterDelegate extends Writer {
    val writer = new java.io.StringWriter

    def writeMessage(message: String): Unit = writer.write(message)
    override def toString: String = writer.toString
  }
  // #snip_3

  // #snip_4
  val myWriterOriginal =
    new StringWriterDelegate

  val myWriterProfanityFirst =
    new StringWriterDelegate with UpperCaseWriter with ProfanityFilteredWriter

  val myWriterProfanityLast =
    new StringWriterDelegate with ProfanityFilteredWriter with UpperCaseWriter

  val msg = "There is no sin except stupidity"
  myWriterOriginal.writeMessage(msg)
  myWriterProfanityFirst.writeMessage(msg)
  myWriterProfanityLast.writeMessage(msg)

  println(myWriterOriginal)
  println(myWriterProfanityFirst)
  println(myWriterProfanityLast)
  // #snip_4

}
