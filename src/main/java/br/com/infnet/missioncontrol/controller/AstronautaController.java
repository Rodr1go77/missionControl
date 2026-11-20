package br.com.infnet.missioncontrol.controller;

import br.com.infnet.missioncontrol.model.Astronauta;
import br.com.infnet.missioncontrol.model.ErrorResponse;
import br.com.infnet.missioncontrol.model.StatusAstronauta;
import br.com.infnet.missioncontrol.service.AstronautaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/astronautas")
public class AstronautaController {

    private final AstronautaService service;

    public AstronautaController(AstronautaService service) {
        this.service = service;
    }

    //GET/astronautas e GET/astronautas?status=STATUS
    @GetMapping
    public ResponseEntity<List<Astronauta>> listar(@RequestParam(required =
            false) StatusAstronauta status) {
        if (status != null) {
            return ResponseEntity.ok(service.filtrarPorStatus(status));
        }

        return ResponseEntity.ok(service.listarTodas());
    }

    // READ - GET /astronautas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorResponse("ID deve ser positivo")));
        }

        Astronauta astronauta = service.buscarPeloId(id);

        if (astronauta == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                    "Astronauta não encontrado"));
        }
        return ResponseEntity.ok(astronauta);
    }

    //CREATE - POST /astronautas - inclui astronauta
    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody Astronauta astronauta) {
        try {
            Astronauta criado = service.adicionar(astronauta);
            return ResponseEntity.status(HttpStatus.CREATED).body(criado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorResponse(e.getMessage())));
        }
    }

    //UPDATE - PUT /astronautas/{id} - atualiza dados de astronauta existente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @Valid @RequestBody Astronauta dadosAtualizados) {

        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorResponse("ID deve ser positivo")));
        }

        Astronauta existente = service.buscarPeloId(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((new ErrorResponse("Astronauta não encontrado.")));
        }

        dadosAtualizados.setId(id);
        try {
            Astronauta atualizado = service.atualizar(id, dadosAtualizados);
            return ResponseEntity.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorResponse(e.getMessage())));
        }

    }
    //DELETE - DELETE /astronautas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((new ErrorResponse("ID deve ser positivo")));
        }
        boolean removido = service.remover(id);

        if (!removido) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body((new ErrorResponse("Astronauta não encontrado.")));
        }

        return ResponseEntity.noContent().build();
    }

}






