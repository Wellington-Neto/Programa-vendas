import { Component, OnInit } from '@angular/core';

import { Cliente} from "../cliente";
import {ClientesService} from "../../clientes.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-clientesform',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesformComponent implements OnInit {

  cliente: Cliente;
  sucess: boolean = false;
  errors: string[];
  id: number;

  constructor( private service: ClientesService,
               private router:Router,
               private activatedRoute: ActivatedRoute) {
    this.cliente = new Cliente();
  }

  ngOnInit(): void {

    this.activatedRoute.params
      .subscribe(urlParams =>{
        this.id = urlParams['id'];
        if(this.id) {
          this.service.getClienteById(this.id)
            .subscribe(response =>
                this.cliente = response,
              errorResponse => this.cliente = new Cliente()
            )
        }
      })
  }

  onSubmit(){
    if(this.id){

      this.service.atualizar(this.cliente)
        .subscribe(response => {
          this.sucess = true;
          this.errors = null;
        }, errorResponse =>{
          this.errors = ['Erro ao atualizar o cliente.'];
        })

    } else {

      this.service.salvar(this.cliente)
        .subscribe(response => {
          this.sucess = true;
          this.errors = null;
          this.cliente = response;
        }, errorResponse => {
          this.sucess = false;
          this.errors = errorResponse.error.errors;
        })
    }
  }

  voltarParaListagem(){
    this.router.navigate(['/clientes-lista']);
  }

}
