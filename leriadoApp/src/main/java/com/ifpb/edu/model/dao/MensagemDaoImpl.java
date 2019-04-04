package com.ifpb.edu.model.dao;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.ifpb.edu.model.domain.Mensagem;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MensagemDaoImpl {
	
	public MongoCollection<Mensagem> getCollection() {
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		MongoClient client = new MongoClient();

		MongoDatabase database = client.getDatabase("chat").withCodecRegistry(pojoCodecRegistry);

		MongoCollection<Mensagem> collection = database.getCollection("mensagens", Mensagem.class);
		
		return collection;
	}
	
	public void criar(Mensagem m) {
		
		getCollection().insertOne(m);
		
		
	}

	public List<Mensagem> recuperarMensagens() {

		FindIterable<Mensagem> mensagens = getCollection().find();
		MongoCursor<Mensagem> todasMensagens = mensagens.iterator();
		List<Mensagem> l = new ArrayList<>();
		while (todasMensagens.hasNext()) {
			l.add(todasMensagens.next());
		}
		return l;
	}
}
