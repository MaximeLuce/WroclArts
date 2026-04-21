import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OrganizationService } from '../../../services/organizations';
import { OrganizationPayload } from '../../../models/organizations';

@Component({
  selector: 'app-organization-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './organization-form.html',
})
export class OrganizationFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private service = inject(OrganizationService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  orgId?: number;

  // ✅ strongly typed form
  form = this.fb.nonNullable.group({
    orgId: [0],
    email: ['', Validators.required],
    nameOrga: ['', Validators.required],
    krsNumber: ['', Validators.required],
    subscriptionDate: ['', Validators.required],
    // ✅ On définit la valeur par défaut et on désactive le champ pour la création
    status: [{ value: 'PendingVerification', disabled: true }, Validators.required],
  });

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.orgId = Number(id);

      // ✅ On est en mode édition : on réactive le champ "status" pour qu'il soit modifiable
      this.form.controls.status.enable();

      this.service.getById(this.orgId).subscribe((organization) => {
        this.form.patchValue({
          orgId: organization.orgId,
          email: organization.email,
          nameOrga: organization.nameOrga,
          krsNumber: organization.krsNumber,
          subscriptionDate: organization.subscriptionDate,
          status: organization.status,
        });
      });
    }
  }

  submit() {
    if (this.form.invalid) return;

    // ✅ getRawValue() est parfait ici car il récupère TOUTES les valeurs,
    // y compris le champ 'status' même s'il est disabled !
    const rawData = this.form.getRawValue();

    // On extrait (et retire) orgId des données qu'on va envoyer
    const { orgId, ...data } = rawData;

    if (this.orgId) {
      // Mode édition (PUT)
      this.service.update(this.orgId, data as OrganizationPayload).subscribe(() => {
        this.router.navigate(['/']); // Pense à changer la route vers ta liste d'organisations
      });
    } else {
      // Mode création (POST)
      this.service.create(data as OrganizationPayload).subscribe(() => {
        this.router.navigate(['/']); // Pense à changer la route vers ta liste d'organisations
      });
    }
  }
}
