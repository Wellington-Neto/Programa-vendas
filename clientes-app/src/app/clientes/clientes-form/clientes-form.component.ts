import { Component, OnInit } from '@angular/core';

import { Cliente} from "../cliente";

@Component({
  selector: 'app-clientesform',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesformComponent implements OnInit {

  cliente: Cliente;

  constructor() { }

  ngOnInit(): void {
  }

}
