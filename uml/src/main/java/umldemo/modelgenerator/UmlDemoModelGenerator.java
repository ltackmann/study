package umlDemo;

import java.io.File;

import com.schantz.foundation.generator.*;
import com.schantz.foundation.generator.metamodel.*;
import com.schantz.foundation.generator.repositories.*;

public class UmlDemoModelGenerator extends ModelGeneratorInterface {
	@Override
	public void execute() {
		JavaBeanGenerator.ormPackage = "umlDemo";
		SdMetaModel metaModel =
			new UmlDemoModelTransformation(new File(getModelPath()+"/umldemo.mdxml"))
				.execute();

		new ModelGenerator(new FileModelRepository())
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
