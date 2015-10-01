package umldemo.modelgenerator;

import java.io.File;

import com.schantz.foundation.generator.metamodel.SdMetaModel;
import com.schantz.foundation.generator.metamodel.transformation.ModelTransformation;

public class UmlDemoModelTransformation extends ModelTransformation {
	public UmlDemoModelTransformation(File xmiFile) {
		super(xmiFile);
	}

	@Override
	public SdMetaModel execute() {
		super.execute();
		return getMetaModel();
	}
}
