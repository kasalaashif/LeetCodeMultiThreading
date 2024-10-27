class FizzBuzz {
    private int n;
    int current = 1;
    Lock lock = new ReentrantLock();
    Condition fizz = lock.newCondition();
    Condition buzz = lock.newCondition();
    Condition fizzBuzz = lock.newCondition();
    Condition number = lock.newCondition();
    
    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while(true)
        {
            lock.lock();
            try
            {
                while(current<=n &&(current%3!=0 || current%5==0))
                    fizz.await();
                if(current>n)
                    break;
                printFizz.run();
                current++;
                signalNext();

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

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(true)
        {
            lock.lock();
            try
            {
                while(current<=n &&(current%5!=0 || current%3==0))
                    buzz.await();
                if(current>n)
                    break;
                printBuzz.run();
                current++;
                signalNext();
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

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(true)
        {
            lock.lock();
            try
            {
                while(current<=n &&(current%3!=0 || current%5!=0))
                    fizzBuzz.await();
                if(current>n)
                    break;
                printFizzBuzz.run();
                current++;
                signalNext();
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

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while(true)
        {
            lock.lock();
            try
            {
                while(current<=n &&(current%3==0 || current%5==0))
                    number.await();
                if(current>n)
                    break;
                printNumber.accept(current);
                current++;
                signalNext();
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

    private void signalNext()
    {
        if(current>n)
        {
            fizz.signalAll();
            buzz.signalAll();
            fizzBuzz.signalAll();
            number.signalAll();
        }
        else if(current%3== 0 && current%5==0)
            fizzBuzz.signal();
        else if(current%3==0)
            fizz.signal();
        else if(current%5==0)
            buzz.signal();
        else
            number.signal();
    }
}