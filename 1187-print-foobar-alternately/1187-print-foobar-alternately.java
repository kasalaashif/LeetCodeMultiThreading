class FooBar {
    private int n;
    Lock lock = new ReentrantLock();
    Condition turn = lock.newCondition();
    boolean firstDone=false;
    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            
        	// printFoo.run() outputs "foo". Do not change or remove this line.   
            lock.lock();
            try
            {
                while(firstDone)
                {
                    turn.await();
                }
        	    printFoo.run();
                firstDone=true;
                turn.signal();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            
            // printBar.run() outputs "bar". Do not change or remove this line.     
            lock.lock();
            try
            {
                while(!firstDone)
                {
                    turn.await();
                }
                printBar.run();
                firstDone = false;
                turn.signal();
            }
             catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                lock.unlock();
            }
        }
    }
}