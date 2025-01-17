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

package chapter6

object Loop extends App {
  // #snip_6-2
  def totalResultOverRange(number: Int, codeBlock: Int => Int) = {
    var result = 0
    for (i <- 1 to number) {
      result += codeBlock(i)
    }
    result
  }
  // #snip_6-2



  // #snip_6-3
  println(totalResultOverRange(11, i => i))
  // #snip_6-3

  // #snip_6-4
  println(totalResultOverRange(11, i => if (i % 2 == 0) i else 0))
  // or use:
  def filterEven(i: Int): Int = if (i % 2 != 0) i else 0
  println(totalResultOverRange(11, filterEven))
  // #snip_6-4

  // #snip_6-5
  println(totalResultOverRange(11, i => if (i % 2 != 0) i else 0))
  // #snip_6-5
}
