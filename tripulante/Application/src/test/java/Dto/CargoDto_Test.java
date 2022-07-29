package Dto;

import Dto.Cargo.CargoDto;
import org.junit.Assert;
import org.junit.Test;

public class CargoDto_Test {

	@Test
	public void CargoCheckPropertiesValid() {
		String descripcion = "Asistente";
		CargoDto cargo2 = new CargoDto();
		CargoDto cargo = new CargoDto(null);

		Assert.assertNull(cargo.Descripcion);

		cargo.setDescripcion(descripcion);

		Assert.assertEquals(descripcion, cargo.getDescripcion());
	}
}
