package classes;

import classes.banco.BancoEvento;
import classes.banco.BancoInscricao;
import classes.banco.BancoParticipante;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Exec {

    private static final Scanner sc = new Scanner( System.in );

    public static void main(String[] args) throws SQLException {

        while ( true ) {
            System.out.println("""
                    ┌————————————————————————————————————————————┐
                    │ BEM VINDO AO SISTEMA DOS EVENTOS CULTURAIS │
                    │ 1. Menu eventos;                           │
                    │ 2. Menu participantes;                     │
                    │ 3. Menu inscrições;                        │
                    │ 0. Sair;                                   │
                    └————————————————————————————————————————————┘
                    """ );
            switch ( (byte)tryNum() ) {
                case 1 : {
                    while( acoesEvento() );

                } break;
                case 2 : {
                    while (acoesParticipante());
                } break;

                case 3 : {
                    while ( acoesInscricao() );
                } break;

                case 0 : {
                    System.exit( 0 );
                }

                default : {
                    System.out.println( "Escolha não existe..." );
                }
            }
        }
    }

    public static boolean acoesEvento( ) throws SQLException {
        System.out.println( """
                               ┌————————————————————————————————————————————┐
                               │                 MENU EVENTO                │
                               │ 1. Criar um evento.                        │
                               │ 2. Apagar um evento.                       │
                               │ 3. Ver todos eventos.                      │
                               │ 4. Buscar eventos por nome.                │
                               │ 0. VOLTAR                                  │
                               └————————————————————————————————————————————┘
                               """ );
        byte escolha = (byte)tryNum();

        switch ( escolha ) {
            case 1 : {
                BancoEvento.adicionarEvento( criaEvento() );
            } break;

            case 2 : {
                System.out.println( "TODOS EVENTOS:" );
                System.out.println( toStringEventos( BancoEvento.buscarTodos() ) );
                System.out.println( "Insira o nome do evento: " );
                sc.nextLine();
                String st = sc.nextLine();
                Evento evento = null;

                try {
                    evento = BancoEvento.buscarEventoPorNome( st );
                    BancoEvento.removerEvento( evento.getId() );
                } catch (NoSuchElementException e ){
                    System.err.println( "Esse evento não está registrado!" );
                } catch ( SQLException sql ) {

                    System.out.println( "Há ao menos uma inscrição para esse evento! Deseja cancelar todas inscrições? s/N" );
                    String sim = sc.nextLine();

                    if ( sim.equals( "s" ) || sim.equals( "S" )) {
                        boolean continuar = true;

                        do {
                            try {
                                BancoInscricao.removerInscricao(BancoInscricao.buscarInscricaoPorNomeDoEvento( evento.getNome() ).getId());
                            } catch ( RuntimeException e ) {
                                continuar = false;
                            }
                        } while ( continuar );

                        BancoEvento.removerEvento( evento.getId() );
                    }
                }

            } break;

            case 3 : {
                System.out.println( toStringEventos( BancoEvento.buscarTodos() ) );
            } break;

            case 4 : {
                System.out.println( "OBSERVAÇÃO: ISSO AQUI SE TORNARIA UM 'EDITAR EVENTO'" );
                System.out.println( "Insira o nome do evento: " );
                sc.nextLine();
                String st = sc.nextLine();

                try {
                    System.out.println(BancoEvento.buscarEventoPorNome( st ));
                } catch (NoSuchElementException e ){
                    System.err.println( "Esse evento não está registrado!" );
                }
            } break;

            case 0 : return false;

            default: {
                System.out.println( "Escolha inválida" );
            } break;
        }
        
        return true;
    }
    public static boolean acoesParticipante( ) throws SQLException {
        System.out.println( """
                               ┌————————————————————————————————————————————┐
                               │             MENU PARTICIPANTE              │
                               │ 1. Cadastrar um participante de eventos.   │
                               │ 2. Remover um participante de eventos.     │
                               │ 3. Ver todos os participantes de eventos.  │
                               │ 4. Buscar um participante por e-mail.      │
                               │ 0. VOLTAR                                  │
                               └————————————————————————————————————————————┘
                               """ );
        byte escolha = (byte)tryNum();

        switch ( escolha ) {
            case 1 : {
                Participante participante = criaParticipante();
                if ( participante != null ) {
                    BancoParticipante.adicionarParticipante(participante);
                }
            } break;

            case 2 : {
                System.out.println( "TODOS PARTICIPANTES:" );
                System.out.println( toStringParticipante( BancoParticipante.buscarTodos() ) );
                System.out.println( "Insira o email do participante: " );
                sc.nextLine();
                String st = sc.nextLine();
                Participante participante = null;

                try {
                    participante = BancoParticipante.buscarParticipantePorEmail( st );
                    BancoParticipante.removerParticipante( participante.getId() );
                } catch (NoSuchElementException e ){
                    System.err.println( "Esse participante não está registrado!" );
                } catch ( SQLException sql ) {

                    System.out.println( "Esse participante está inscrito em ao menos um evento! Deseja cancelar todas inscrições? s/N" );
                    String sim = sc.nextLine();

                    if ( sim.equals( "s" ) || sim.equals( "S" )) {
                        boolean continuar = true;

                        do {
                            try {
                                BancoInscricao.removerInscricao(BancoInscricao.buscarInscricaoPorEmailDoParticipante( participante.getEmail() ).getId());
                            } catch ( RuntimeException e ) {
                                continuar = false;
                            }
                        } while ( continuar );

                        BancoParticipante.removerParticipante( participante.getId() );
                    }
                }

            } break;

            case 3 : {
                System.out.println( toStringParticipante( BancoParticipante.buscarTodos() ) );
            } break;

            case 4 : {
                System.out.println( "OBSERVAÇÃO: ISSO AQUI SE TORNARIA UM 'EDITAR PARTICIPANTE'" );
                System.out.println( "Insira o nome do participante: " );
                sc.nextLine();
                String st = sc.nextLine();

                try {
                    System.out.println(BancoParticipante.buscarParticipantePorEmail( st ));
                } catch (NoSuchElementException e ){
                    System.err.println( "Esse participante não está registrado!" );
                }
            } break;

            case 0 : return false;

            default: {
                System.out.println( "Escolha inválida" );
            } break;
        }
        
        return true;
    }
    public static boolean acoesInscricao( ) {
        System.out.println( """
                               ┌———————————————————————————————————————————————————┐
                               │                   MENU INSCRIÇÃO                  │
                               │ 1. Realizar uma inscrição.                        │
                               │ 2. Cancelar uma inscrição.                        │
                               │ 3. Ver todas inscrições.                          │
                               │ 4. Buscar inscrições por e-mail do participante.  │
                               │ 5. Buscar inscrições por nome do evento.          │
                               │ 0. VOLTAR                                         │
                               └———————————————————————————————————————————————————┘
                               """ );
        byte escolha = (byte)tryNum();

        switch ( escolha ) {
            case 1 : {
                ArrayList<Evento> evento = BancoEvento.buscarTodos();
                ArrayList<Participante> participante = BancoParticipante.buscarTodos();
                if ( evento.isEmpty() || participante.isEmpty() ) {
                    System.err.printf("""
                            ATENÇÃO, Há um total de:
                            %d participante(s) e
                            %d evento(s) cadastrado(s)...
                            
                            """, participante.size(), evento.size());
                } else {
                    sc.nextLine();
                    System.out.println(toStringEventos(evento));
                    System.out.print("Insira o nome do evento: ");
                    String nome = sc.nextLine();

                    System.out.println(toStringParticipante(participante));
                    System.out.print("Insira o e-mail do participante: ");
                    String email = sc.nextLine();

                    try {
                        int eventoId = BancoEvento.buscarEventoPorNome(nome).getId();
                        int participanteId = BancoParticipante.buscarParticipantePorEmail(email).getId();
                        BancoInscricao.inscreverParticipante(eventoId, participanteId);

                    } catch (RuntimeException e) {
                        System.err.println("Evento ou participante não encontrado.");
                    }
                }
            } break;

            case 2 : {
                System.out.println( "TODAS INSCRIÇÕES:" );
                System.out.println( toStringInscricoes( BancoInscricao.buscarTodas() ) );

                if ( !BancoInscricao.buscarTodas().isEmpty() ) {
                    System.out.println( "Insira o id da inscrição: " );

                    int id = (int)tryNum();

                    try {
                        BancoInscricao.removerInscricao( id );
                    } catch (NoSuchElementException e ){
                        System.err.println( "Essa inscrição não existe!" );
                    }
                }
//                catch ( SQLException sql ) {
//
//                    System.out.println( "Há ao menos uma inscrição para esse evento! Deseja cancelar todas inscrições? s/N" );
//                    String sim = sc.nextLine();
//
//                    if ( sim.equals( "s" ) || sim.equals( "S" )) {
//                        boolean continuar = true;
//
//                        do {
//                            try {
//                                BancoInscricao.removerInscricao(BancoInscricao.buscarInscricaoPorNomeDoEvento( evento.getNome() ).getId());
//                            } catch ( RuntimeException e ) {
//                                continuar = false;
//                            }
//                        } while ( continuar );
//
//                        BancoEvento.removerEvento( evento.getId() );
//                    }
//                }

            } break;

            case 3 : {
                System.out.println( toStringInscricoes( BancoInscricao.buscarTodas() ) );
            } break;

            case 4 : {
                System.out.println( "Insira o e-mail do participante: " );
                sc.nextLine();
                String st = sc.nextLine();

                try {
                    System.out.println(
                            BancoInscricao.buscarInscricaoPorEmailDoParticipante(
                                    BancoParticipante.buscarParticipantePorEmail( st ).getEmail() ) );

                } catch (NoSuchElementException e ) {
                    System.err.println( "Inscrição ou e-mail não está registrado(a)!" );
                }

            } break;

            case 5 : {
                System.out.println( "Insira o nome do evento: " );
                sc.nextLine();
                String st = sc.nextLine();

                try {
                    System.out.println(
                            BancoInscricao.buscarInscricaoPorNomeDoEvento(
                                    BancoEvento.buscarEventoPorNome( st ).getNome() ) );

                } catch (NoSuchElementException e ) {
                    System.err.println( "Inscrição ou evento não está registrado(a)!" );
                }

            } break;

            case 0 : return false;

            default: {
                System.out.println( "Escolha inválida" );
            } break;
        }

        return true;
    }

    private static Participante criaParticipante() {
        sc.nextLine();
        System.out.print( "Insira o e-mail do participante (Deve ser único!) : " );
        String email = sc.nextLine();
        boolean registrado = true;
        try {
            BancoParticipante.buscarParticipantePorEmail( email );
        } catch ( RuntimeException e ) {
            registrado = false;
        }
        if ( registrado ) {
            System.out.println( "E-mail já registrado!!" );
            return null;
        }
        System.out.print( "Insira o nome do participante: " );
        String nome = sc.nextLine();
        return new Participante( nome , email );
    }
    private static Evento criaEvento() {
        sc.nextLine();
        System.out.print( "Insira um nome: " );
        String nome = sc.nextLine();
        System.out.print( "Insira o local do evento: " );
        String local = sc.nextLine();

        System.out.print( "Insira o dia do evento: " );
        byte dia = (byte)tryNum();
        System.out.print( "Insira o mês do evento: " );
        byte mes = (byte)tryNum();
        System.out.print( "Insira o ano do evento: " );
        int ano = (int)tryNum();
        LocalDate data = LocalDate.of( ano , mes , dia );
        sc.nextLine();
        System.out.print( "Insira a descrição do evento: " );
        String descricao = sc.nextLine();

        return new Evento( nome , local , data , descricao );
    }

    private static String toStringEventos( ArrayList<Evento> eventos ) {
        StringBuilder string = new StringBuilder();
        for ( Evento evento : eventos ) {
            string.append(evento);
        }
        return (eventos.isEmpty()) ? "Nenhum evento cadastrado" : string.toString();
    }
    private static String toStringParticipante( ArrayList<Participante> participantes ) {
        StringBuilder string = new StringBuilder();
        for ( Participante participante : participantes ) {
            string.append(participante);
        }
        return (participantes.isEmpty()) ? "Nenhum participante cadastrado" : string.toString();
    }
    private static String toStringInscricoes( ArrayList<Inscricao> inscricoes ) {
        StringBuilder string = new StringBuilder();
        for ( Inscricao inscricao : inscricoes ) {
            string.append( inscricao );
        }
        return (inscricoes.isEmpty()) ? "Nenhuma inscrição realizada" : string.toString();
    }

    public static void testesEvento( String nomeEvento ) throws SQLException {
        Evento evento =
                new Evento( nomeEvento , "Deuschland" , LocalDate.now() , "viajar uma viagem" );

        BancoEvento.adicionarEvento( evento );

        Evento eventoComId =
        BancoEvento.buscarEventoPorNome( nomeEvento );

        System.out.println( eventoComId );

        BancoEvento.adicionarEvento( evento );

        System.out.println( BancoEvento.buscarTodosEventosPorUmNome( nomeEvento ) );

        BancoEvento.removerEvento( eventoComId.getId() );

        System.out.println( BancoEvento.buscarTodos() );

        BancoEvento.removerEvento( eventoComId.getId() + 1 );
    }
    public static void testesParticipante( String nomeParticipante ) throws SQLException {

        String email = nomeParticipante.toLowerCase() + "@gmail.com";
        Participante participante =
                new Participante( nomeParticipante , email );

        BancoParticipante.adicionarParticipante( participante );

        Participante participanteComId =
                BancoParticipante.buscarParticipantePorEmail( email );

        System.out.println( participanteComId );

        BancoParticipante.adicionarParticipante( participante );

        System.out.println( BancoParticipante.buscarParticipantePorEmail( participante.getEmail() ) );

        BancoParticipante.removerParticipante( participanteComId.getId() );

        System.out.println( BancoParticipante.buscarTodos() );
    }
    public static void testesInscricao( String nomeParticipante , String nomeEvento ) {

        BancoEvento.adicionarEvento(
                new Evento( nomeEvento , "Belarus" , LocalDate.now() , "Ich bin slav" ) );
        Evento evento =
                BancoEvento.buscarEventoPorNome( nomeEvento );

        String email = nomeParticipante.toLowerCase() + "@gmail.com";
        BancoParticipante.adicionarParticipante( new Participante( nomeParticipante , email ) );
        Participante participante =
                BancoParticipante.buscarParticipantePorEmail( email );

        BancoInscricao.inscreverParticipante(evento.getId(), participante.getId());

        Inscricao inscricao = BancoInscricao.buscarInscricaoPorEmailDoParticipante( email );

        System.out.println( "POR ID" );
        System.out.println( BancoInscricao.buscarInscricaoPorId( inscricao.getId() ) );

        System.out.println( "TODAS" );
        System.out.println( BancoInscricao.buscarTodas() );

        BancoInscricao.removerInscricao( inscricao.getId() );

        System.out.println( "TODAS" );
        System.out.println( BancoInscricao.buscarTodas() );
    }

    private static double tryNum() {
        double num = 0;
        boolean erro = true;
        do {
            try {
                num = sc.nextDouble();
                erro = false;
            } catch ( InputMismatchException e ) {
                System.out.println( "Insira um valor válido." );
                sc.next();
            }
        } while (erro);

        return num;

    }
}
