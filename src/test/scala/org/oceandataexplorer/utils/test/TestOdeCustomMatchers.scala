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

import org.scalatest.{FlatSpec, Matchers}


/**
 * Tests for OdeCustomMatchers
 *
 * @author Alexandre Degurse
 */
class TestOdeCustomMatchers extends FlatSpec with Matchers with OdeCustomMatchers {

  // Import ErrorMetrics._ for implicits rmse functions
  import ErrorMetrics._
  val maxRMSE = 1.0E-10

  "rmseMatcher" should "should find match when given the same array" in {
    val data = Array(
      0.1221174368357885, 0.8030612655311997, 0.8732426284336273,
      0.8000925604778708, 0.6351656368136573, 0.323284190497698 ,
      0.6192489942098376, 0.9573403084388671, 0.7101131243855894,
      0.0232360227774637
    )

    data should new RmseMatcher[Array[Double]](maxRMSE, data)
  }
}
