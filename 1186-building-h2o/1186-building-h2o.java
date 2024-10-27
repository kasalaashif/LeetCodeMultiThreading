class H2O {
    private Semaphore hydrogenSemaphore = new Semaphore(2);
    private Semaphore oxygenSemaphore = new Semaphore(1);
    private CyclicBarrier barrier = new CyclicBarrier(3);
    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSemaphore.acquire();
        try
        {
            barrier.await();
            releaseHydrogen.run();
        }
        catch(Exception ie)
        {
            ie.printStackTrace();
        }
        finally
        {
            hydrogenSemaphore.release();
        }
		
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        
        oxygenSemaphore.acquire();
        try
        {
            barrier.await();
            releaseOxygen.run();
        }
        catch(Exception ie)
        {
            ie.printStackTrace();
        }
        finally
        {
            oxygenSemaphore.release();
        }
    }
}