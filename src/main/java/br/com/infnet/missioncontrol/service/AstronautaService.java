package br.com.infnet.missioncontrol.service;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AstronautaService {

    private final ArrayList<Astronauta> astronautas = new ArrayList<>();
    private Long proximoID = 4L;

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
                StatusAstronauta.AFASTADO

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
            if (status != null && status.equals(astronauta.getStatus())){
                resultado.add(astronauta);
            }
        }
        return resultado;
    }
}