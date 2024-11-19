package unipar.integrador.mywallet.api.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metas-usuario")
public class MetasUsuarioController {

//    @Autowired
//    private IMetasUsuario metasService;
//
//    @PostMapping
//    public ResponseEntity<MetasUsuarioEntity> create(@Valid @RequestBody MetasUsuarioDTO dto) {
//        MetasUsuarioEntity metas = metasService.save(dto);
//        return new ResponseEntity<>(metas, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<MetasUsuarioEntity> findById(@PathVariable UUID id) {
//        Optional<MetasUsuarioEntity> metas = metasService.findById(id);
//        return metas.map(ResponseEntity::ok)
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<MetasUsuarioEntity>> findAll() {
//        List<MetasUsuarioEntity> metas = metasService.findAll();
//        return new ResponseEntity<>(metas, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<MetasUsuarioEntity> update(@PathVariable UUID id, @Valid @RequestBody MetasUsuarioDTO dto) {
//        MetasUsuarioEntity metas = metasService.update(id, dto);
//        return new ResponseEntity<>(metas, HttpStatus.OK);
//    }
}
