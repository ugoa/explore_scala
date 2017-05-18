def insert(x: Int, xs: List[Int]): List[Int] =
  if (xs.isEmpty || x <= xs.head) x::xs else xs.head :: insert(x, xs.tail)

def isort(xs: List[Int]): List[Int] =
  if (xs.isEmpty) Nil else insert(xs.head, isort(xs.tail))


def pinsert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => list()
  case y :: ys => 
    if (x <= y) y :: ys else y :: pinsert(x, ys)
}

def pisort(xs: List[Int]): List[Int] = xs match {
  case List() => Nil
  case x: xs1 => insert(x, pinsert(xs1))
}
