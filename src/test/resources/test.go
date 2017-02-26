package main

func SimpleWorker(workQueue <-chan SpaceCube, resultQueue chan<- SpaceCube) {
  SendMesssage(func() { resultQueue <- cube })
}