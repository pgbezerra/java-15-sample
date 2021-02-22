package com.pgbezerra.sample.service;

import java.util.function.Consumer;

public final class SeleadServiceImpl implements SeleadService {

	@Override
	public void printaAlgoAi() {
		Consumer<String> vouDesacloparTudo = System.out::println;
		vouDesacloparTudo.accept("Printado");
	}

}
