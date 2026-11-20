package br.com.infnet.missioncontrol.service;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AstronautaService {

    private final ArrayList<Astronauta> astronautas = new ArrayList<>();
    private Long proximoId = 4L;

    //Construtor
    public AstronautaService() {

        astronautas.add(new Astronauta(
                1L,
                "Ana Silva",
                "Brasil",
                34,
                8,
                StatusAstronauta.DISPONIVEL
        ));

        astronautas.add(new Astronauta(
                2L,
                "John Carter",
                "Estados Unidos",
                41,
                15,
                StatusAstronauta.EM_MISSAO
        ));

        astronautas.add(new Astronauta(
                3L,
                "Mei Lin",
                "China",
                29,
                5,
                StatusAstronauta.EM_MISSAO

        ));
    }

    //Buscar toda a lista
    public List<Astronauta> listarTodas() {
        return astronautas;
    }

    //Busca filtrada por status
    public List<Astronauta> filtrarPorStatus(StatusAstronauta status) {
        List<Astronauta> resultado = new ArrayList<>();
        for (Astronauta astronauta : astronautas) {
            if (status != null && status.equals(astronauta.getStatus())) {
                resultado.add(astronauta);
            }
        }
        return resultado;
    }

    //Busca pelo ID
    public Astronauta buscarPeloId(Long id) {
        for (Astronauta astronauta : astronautas) {
            if (astronauta.getId() != null && (astronauta.getId().equals(id))) {
                return astronauta;
            }
        }
        return null;
    }

    //Remover Astronauta
    public boolean remover(Long id) {
        for (int i = 0; i < astronautas.size(); i++) {
            if (astronautas.get(i).getId() != null && astronautas.get(i).getId().equals(id)) {
                astronautas.remove(i);
                return true;
            }
        }
        return false;
    }

    //Cadastrar astronauta
    public Astronauta adicionar(Astronauta astronauta) {
        for (Astronauta existente : astronautas) {
            if (existente.getNome() != null && existente.getNome().trim().equalsIgnoreCase(astronauta.getNome().trim())) {
                throw new IllegalArgumentException("Já existe um astronauta com " +
                        "este nome.");
            }
        }

        astronauta.setId(proximoId++);

        astronautas.add(astronauta);

        return astronauta;
    }

    public Astronauta atualizar(Long id, Astronauta dadosAtualizados) {
        for (int i = 0; i < astronautas.size(); i++) {
            Astronauta atual = astronautas.get(i);

            if (atual.getId() != null && atual.getId().equals(id)) {
                for (Astronauta outro : astronautas) {
                    if (outro.getId() != null && !outro.getId().equals(id)
                            && outro.getNome() != null && dadosAtualizados.getNome() != null
                            && outro.getNome().trim().equalsIgnoreCase(dadosAtualizados.getNome().trim()))
                        throw new IllegalArgumentException("Já existe um " +
                                "astronauta com esse nome");
                }
                dadosAtualizados.setId(id);
                astronautas.set(i, dadosAtualizados);
                return dadosAtualizados;
            }
        }
        return null;
    }
}





