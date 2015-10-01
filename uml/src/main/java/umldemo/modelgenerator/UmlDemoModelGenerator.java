package umldemo.modelgenerator;

import java.io.File;

import com.schantz.foundation.generator.*;
import com.schantz.foundation.generator.metamodel.*;
import com.schantz.foundation.generator.repositories.*;

public class UmlDemoModelGenerator extends ModelGeneratorInterface {

	public UmlDemoModelGenerator() {
		// Initialize stereotypes
		SdMetaStereotype.init();
	}
	
	@Override
	public void execute() {
		SdMetaModel metaModel =
			new UmlDemoModelTransformation(new File(getModelPath()+"/umldemo.mdxml"))
				.execute();

		new OrmModelGenerator(new FileModelRepository())
  		.setMetaModel(metaModel)
  		.setOrmFilename("UmlDemoModelOrm.xml")
  		.setEnumInitializerClassName("umlDemo.EnumInitializer")
		.setDestination(getModelPath() + "/../src/model/java")
		.setOrmFileDestination(getModelPath() + "/../src/main/resources")
		.execute();
	}
	
	public static void main(String[] args) {
		new UmlDemoModelGenerator().execute();
	}
}
