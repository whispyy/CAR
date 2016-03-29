public class Worker extends UntypedActor {

	@Override
	public void onReceive(Object message) {
		if (message instanceof Work) {
			BigInteger bigInt = new CalculateFactorial().calculate();
			getSender().tell(new Result(bigInt), getSelf());
		} else
			unhandled(message);
	}

	public static Props createWorker() {
		return Props.create(Worker.class, new ArraySeq<Object>(0));
	}
}