(ns math.numbers-test
  (:require [clojure.test :refer :all]
            [math.generic :as g]
            [math.numbers]))

(deftest arithmetic
  (testing "with-numbers"
    (is (= 4 (g/+ 2 2)))
    (is (= 3.5 (g/+ 1.5 2)))
    (is (= 13/40 (g/+ 1/5 1/8)))
    (is (= 1/8 (g/- 3/8 1/4)))
    (is (= 20 (g/* 5 4)))
    (is (= 5 (g// 20 4)))
    (is (= 5/4 (g// 5 4)))
    (is (= 1/8 (g// 8))))
  (testing "more-numbers"
    (is (= 10 (g/+ 1 2 3 4)))
    (is (= -14 (g/- 10 9 8 7)))
    (is (= 0 (g/+)))
    (is (= 3.14 (g/+ 3.14)))
    (is (= 1 (g/*)))
    (is (= 2 (g/* 2)))
    (is (= 4 (g/* 2 2)))
    (is (= 8 (g/* 2 2 2)))
    (is (= 1 (g// 1)))
    (is (= 1/2 (g// 1 2)))
    (is (= 1/4 (g// 1 2 2)))
    (is (= 1/8 (g// 1 2 2 2)))
    (is (= 2.14 (g/- 3.14 1))))
  (testing "trig"
    (is (= 1.0 (g/cos 0)))
    (is (= 0.0 (g/sin 0)))
    )
  (testing "square/cube"
    (is (= 4 (g/square 2)))
    (is (= 4 (g/square -2)))
    (is (= 27 (g/cube 3)))
    (is (= -27 (g/cube -3)))
    )
  (testing "with-symbols"
    (is (= '(math.generic/+ 4 x) (g/+ 4 'x)))
    (is (= '(math.generic/+ 5 y) (g/+ 'y 5)))
    (is (= '(math.generic// 5 y) (g// 5 'y)))
    (is (= '(math.generic/* 5 y) (g/* 5 'y)))
    (is (= '(math.generic// x y) (g// 'x 'y)))
    (is (= '(math.generic/* x y) (g/* 'x 'y)))
    )
  (testing "zero/one elimination"
    (is (= 'x (g/+ 0 'x)))
    (is (= 'x (g/* 1 'x)))
    (is (= (g/negate 'x) (g/- 0 'x)))
    (is (= 'x (g/+ 'x 0)))
    (is (= 'x (g/* 'x 1)))
    (is (= 'x (g/- 'x 0)))
    (is (= 'x (g/+ 0.0 'x)))
    (is (= 'x (g/* 1.0 'x)))
    (is (= 'x (g/+ 'x 0.0)))
    (is (= 'x (g/* 'x 1.0)))
    (is (= 'x (g// 'x 1.0)))
    (is (= 'x (g// 'x 1)))
    (is (= 0 (g// 0 'x)))
    (is (= 0 (g/* 0 'x)))
    (is (= 0 (g/* 'x 0)))
    (is (thrown? ArithmeticException (g// 'x 0)))
    )
  (testing "neg"
    (is (= -4 (g/- 0 4)))
    (is (= (g/negate 'x) (g/- 0 'x)))
    (is (= -4 (g/- 4)))
    (is (= -4.2 (g/- 4.2)))
    )
  (testing "zero?"
    (is (g/zero? 0))
    (is (not (g/zero? 1)))
    (is (g/zero? 0.0))
    (is (not (g/zero? 1.0)))
    (is (g/one? 1))
    (is (not (g/one? 2)))
    (is (g/one? 1.0))
    (is (not (g/one? 0.0)))
    )
  (testing "zero-like"
    (is (= 0 (g/zero-like 2)))
    (is (= 0.0 (g/zero-like 3.14)))
    )
  (testing "abs"
    (is (= 1 (g/abs -1)))
    (is (= 1 (g/abs 1)))
    (is (= '(math.generic/abs x) (g/abs 'x)))
    )
  (testing "sqrt"
    (is (= 9 (g/sqrt 81)))
    (is (= '(math.generic/sqrt x) (g/sqrt 'x)))
    )
  (testing "expt"
    (is (= 32 (g/expt 2 5)))
    (is (= '(math.generic/expt x y) (g/expt 'x 'y)))
    (is (= '(math.generic/expt 2 y) (g/expt 2 'y)))
    (is (= 1 (g/expt 1 'x)))
    (is (= 1 (g/expt 'x 0)))
    (is (= 'x (g/expt 'x 1)))
    (is (= 'x (g/expt (g/sqrt 'x) 2)))
    (is (= '(math.generic/expt x 3) (g/expt (g/sqrt 'x) 6)))
    (is (= '(math.generic/expt x 12) (g/expt (g/expt 'x 4) 3)))
    (is (= '(math.generic// 1 (math.generic/expt x 3)) (g/expt 'x -3)))
    )
  (testing "exp/log"
    (is (= 1.0 (g/exp 0)))
    (is (= '(math.generic/exp x) (g/exp 'x)))
    (is (= 0.0 (g/log 1)))
    (is (= '(math.generic/log x) (g/log 'x)))
    (is (= 0.0 (g/log (g/exp 0))))
    )
)
