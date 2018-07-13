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

import org.oceandataexplorer.engine.workflows._
import org.scalatest._
import matchers._


/**
 * Custom matchers for tests
 *
 * @author Alexandre Degurse
 */

trait RmseMatchers {
  class RmseMatcher[T](maxRMSE: Double, expected: T) extends Matcher[T] {
    def apply(actual: T) = {
      val rmse = (actual, expected) match {
        case _: (Double, Double) =>
          ErrorMetrics.rmse(
            actual.asInstanceOf[Double],
            expected.asInstanceOf[Double]
          )
        case _: (Array[Double], Array[Double]) =>
          ErrorMetrics.rmse(
            actual.asInstanceOf[Array[Double]],
            expected.asInstanceOf[Array[Double]]
          )
        case _: (Array[SegmentedRecord], Array[SegmentedRecord]) =>
          ErrorMetrics.rmse(
            actual.asInstanceOf[Array[SegmentedRecord]],
            expected.asInstanceOf[Array[SegmentedRecord]]
          )
        case _: (Array[AggregatedRecord], Array[AggregatedRecord]) =>
          ErrorMetrics.rmse(
            actual.asInstanceOf[Array[AggregatedRecord]],
            expected.asInstanceOf[Array[AggregatedRecord]]
          )
        case _ => throw new IllegalArgumentException("Wrong argument types for RmseMatcher")
      }


      MatchResult(
        rmse < maxRMSE,
        s"The arrays did not rmse-match ($rmse > $maxRMSE)",
        s"The arrays did rmse-match (with rmse of $rmse)"
      )
    }
  }

  private def rmseMatch[T](maxRMSE: Double)(expected: T) = new RmseMatcher[T](maxRMSE, expected)
  def rmseMatcher[T](maxRMSE: Double) = rmseMatch[T](maxRMSE)(_)
}