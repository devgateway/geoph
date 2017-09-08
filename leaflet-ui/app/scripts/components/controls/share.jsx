import React from 'react';
import {connect} from 'react-redux'
import translate from '../../util/translate.js';
import { shareMap } from '../../actions/saveAndRestoreMap';
import CopyToClipboard from 'react-copy-to-clipboard';
import { ShareButtons, generateShareIcon } from 'react-share';

require('./share.scss');

class Share extends React.Component {
  
  shareMap() {
    this.props.onShareMap();
  }
  
  render() {
    const { isShareNeeded, shareUrl, visible } = this.props;
    
    if (isShareNeeded) {
      this.shareMap();
    }
    const {
      FacebookShareButton,
      GooglePlusShareButton,
      LinkedinShareButton,
      TwitterShareButton,
      VKShareButton
    } = ShareButtons;
    
    const FacebookIcon = generateShareIcon('facebook');
    const TwitterIcon = generateShareIcon('twitter');
    const GooglePlusIcon = generateShareIcon('google');
    const LinkedinIcon = generateShareIcon('linkedin');
    const VKIcon = generateShareIcon('vk');
    
    
    return (
      <div>
        {visible ?
          <div className="share-container">
            {isShareNeeded ?
              <div className="loading-css">
                <div></div>
              </div>
              :
              <div>
                <h2>{translate('header.settings.share')}</h2>
                <div>
                  <ul className="share">
                    <li>
                      <FacebookShareButton url={shareUrl} title={translate('header.share.title')}>
                        <FacebookIcon size={32} round/>
                      </FacebookShareButton>
                    </li>
                    <li>
                      <TwitterShareButton url={shareUrl} title={translate('header.share.title')}>
                        <TwitterIcon size={32} round/>
                      </TwitterShareButton>
                    </li>
                    <li>
                      <GooglePlusShareButton url={shareUrl} title={translate('header.share.title')}>
                        <GooglePlusIcon size={32} round/>
                      </GooglePlusShareButton>
                    </li>
                    <li>
                      <LinkedinShareButton url={shareUrl} title={translate('header.share.title')}>
                        <LinkedinIcon size={32} round/>
                      </LinkedinShareButton>
                    </li>
                    <li>
                      <VKShareButton url={shareUrl} title={translate('header.share.title')}>
                        <VKIcon size={32} round/>
                      </VKShareButton>
                    </li>
                  </ul>
                </div>
                <div className="share-link">
                  <input className="form-control" type="text" value={isShareNeeded ? '' : shareUrl} onChange={() => {}}/>
                  <CopyToClipboard text={shareUrl}>
                    <button className="btn btn-sm btn-success" disabled={isShareNeeded ? true : false}>Copy URL</button>
                  </CopyToClipboard>
                </div>
              </div>
            }
          </div>
          
          : null}
      </div>
    );
  }
}

const mapDispatchToProps = (dispatch, ownProps) => {
  return {
    onShareMap: () => {
      dispatch(shareMap());
    },
  }
};

const mapStateToProps = (state, props) => {
  return state.share.toJS();
};

export default connect(mapStateToProps, mapDispatchToProps)(Share);
