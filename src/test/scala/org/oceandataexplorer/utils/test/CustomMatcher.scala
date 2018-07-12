/** Copyright (C) 2017-2018 Project-ODE
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.oceandataexplorer.utils.test

import org.scalatest._
import matchers._


/**
 * Custom matchers for tests
 *
 * @author Alexandre Degurse
 */

trait CustomMatchers {

  class ArrayRMSEMatch(expected: Array[Double]) extends Matcher[Array[Double]] {

    def apply(actual: Array[Double]) = {
      val rmse = ErrorMetrics.rmse(actual, expected)
      val maxRMSE = 1.0E-10
      MatchResult(
        ErrorMetrics.rmse(actual, expected) < 1.0E-10,
        s"""The arrays did not rmse-match ($rmse > $maxRMSE)"""",
        s"""The arrays did rmse-match (with rmse of $rmse)""""
      )
    }
  }

  def rmseMatch(expected: Array[Double]) = new ArrayRMSEMatch(expected)
}
