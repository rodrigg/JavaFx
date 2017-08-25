package de.saxsys.mvvmfx.examples.contacts.model.countries;

import de.saxsys.mvvmfx.examples.contacts.model.Country;
import de.saxsys.mvvmfx.testingutils.jfxrunner.JfxRunner;
import org.datafx.reader.converter.XmlConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JfxRunner.class)
public class DataFxCountrySelectorIntegrationTest extends AbstractCountrySelectorTest {

	private CountrySelector countrySelector;


	@Override
	protected CountrySelector getCountrySelector() {
		return countrySelector;
	}

	@Before
	public void setup() {
		countrySelector = new DataFxCountrySelector();
	}

	@Test
	public void testXmlConverterForCountry() throws FileNotFoundException {
		XmlConverter<Country> converter = new XmlConverter<>("iso_3166_entry", Country.class);

		InputStream iso_3166_xml = this.getClass().getResourceAsStream("/countries/iso_3166.xml");

		assertThat(iso_3166_xml).isNotNull();

		converter.initialize(iso_3166_xml);

		Country country = converter.get();
		assertThat(country).isNotNull();
	}

	@Test
	public void testXmlConverterForSubdivision() throws Exception {
		XmlConverter<ISO3166_2_CountryEntity> converter = new XmlConverter<>("iso_3166_country",
				ISO3166_2_CountryEntity.class);

		InputStream iso_3166_2_xml = this.getClass().getResourceAsStream("/countries/iso_3166_2.xml");

		assertThat(iso_3166_2_xml).isNotNull();

		converter.initialize(iso_3166_2_xml);

		ISO3166_2_CountryEntity entity = converter.get();

		assertThat(entity).isNotNull();
		assertThat(entity.code).isNotNull().isEqualTo("DE");

		assertThat(entity.subsets).isNotNull().hasSize(1);
		assertThat(entity.subsets.get(0).subdivisionType).isEqualTo("State");

		List<ISO3166_2_EntryEntity> entryList = entity.subsets.get(0).entryList;

		assertThat(entryList).isNotNull().hasSize(16);

		ISO3166_2_EntryEntity entry = entryList.get(0);

		assertThat(entry.code).isEqualTo("DE-BW");
		assertThat(entry.name).isEqualTo("Baden-Württemberg");
	}

}
