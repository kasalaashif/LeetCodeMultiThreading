class ZeroEvenOdd {
    private int n;
    Lock lock = new ReentrantLock();
    Condition zeroThread = lock.newCondition();
    Condition evenThread = lock.newCondition();
    Condition oddThread = lock.newCondition();
    boolean oddDone= false;
    boolean evenDone= false;
    boolean zeroDone= false;
    int current = 1;
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
       for(int i=0; i<n; i++)
        {
            lock.lock(); 
            try
            {
                while(zeroDone)
                    zeroThread.await();
                printNumber.accept(0);
                zeroDone = true;
                if(current%2 ==0)
                    evenThread.signal();  
                else
                    oddThread.signal();
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

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i=2; i<=n; i=i+2)
        {
            lock.lock(); 
            try
            {
                while(!zeroDone || current%2!=0)
                    evenThread.await();

                printNumber.accept(current);
                evenDone = true;
                zeroDone = false;
                current++;
                zeroThread.signal();
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

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1; i<=n; i= i+2)
        {
            lock.lock(); 
            try
            {
                while(!zeroDone || current%2==0)
                    oddThread.await();

                printNumber.accept(current);
                oddDone = true;
                zeroDone = false;
                current++;
                zeroThread.signal();
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